import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ModeVieDetailComponent } from 'app/entities/mode-vie/mode-vie-detail.component';
import { ModeVie } from 'app/shared/model/mode-vie.model';

describe('Component Tests', () => {
  describe('ModeVie Management Detail Component', () => {
    let comp: ModeVieDetailComponent;
    let fixture: ComponentFixture<ModeVieDetailComponent>;
    const route = ({ data: of({ modeVie: new ModeVie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ModeVieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModeVieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModeVieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modeVie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modeVie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
