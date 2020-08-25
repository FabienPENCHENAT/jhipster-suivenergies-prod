import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD007ParoiOpaqueDetailComponent } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque-detail.component';
import { TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

describe('Component Tests', () => {
  describe('TD007ParoiOpaque Management Detail Component', () => {
    let comp: TD007ParoiOpaqueDetailComponent;
    let fixture: ComponentFixture<TD007ParoiOpaqueDetailComponent>;
    const route = ({ data: of({ tD007ParoiOpaque: new TD007ParoiOpaque(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD007ParoiOpaqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD007ParoiOpaqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD007ParoiOpaqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD007ParoiOpaque on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD007ParoiOpaque).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
