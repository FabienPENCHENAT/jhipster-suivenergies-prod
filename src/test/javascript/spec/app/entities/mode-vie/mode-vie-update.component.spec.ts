import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ModeVieUpdateComponent } from 'app/entities/mode-vie/mode-vie-update.component';
import { ModeVieService } from 'app/entities/mode-vie/mode-vie.service';
import { ModeVie } from 'app/shared/model/mode-vie.model';

describe('Component Tests', () => {
  describe('ModeVie Management Update Component', () => {
    let comp: ModeVieUpdateComponent;
    let fixture: ComponentFixture<ModeVieUpdateComponent>;
    let service: ModeVieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ModeVieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModeVieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeVieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeVieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModeVie(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModeVie();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
